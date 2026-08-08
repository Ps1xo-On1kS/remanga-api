package org.on1ks.remanga.api

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ApiExtractorTest {
    @Test
    fun `extracts page metadata`() {
        val html = """{\"GIT_HASH\":\"abc123\"}<script src="/_next/static/a.js"></script><script src="/_next/static/a.js"></script>"""
        assertEquals(PageMetadata("abc123", listOf("/_next/static/a.js")), ApiExtractor.pageMetadata(html))
    }

    @Test
    fun `extracts and deduplicates endpoints`() {
        val javascript = """client.post({scheme:"bearer",headers:{"Content-Type":"application/json"},url:"/api/v2/users/{user_id}/"})"""
        val endpoints = ApiExtractor.endpoints(listOf(BundleSource("/_next/static/test.js", javascript), BundleSource("/_next/static/test.js", javascript)))
        assertEquals(1, endpoints.size)
        val endpoint = endpoints.single()
        assertEquals("POST", endpoint.method)
        assertEquals("users", endpoint.group)
        assertEquals(listOf("user_id"), endpoint.pathParameters)
        assertEquals("application/json", endpoint.contentType)
        assertTrue(endpoint.bearerCapable)
        assertEquals(listOf("test.js"), endpoint.sourceBundles)
    }

    @Test
    fun `does not mark get content type`() {
        val endpoint = ApiExtractor.endpoints(listOf(BundleSource("a.js", """client.get({headers:{"Content-Type":"application/json"},url:"/api/search/"})"""))).single()
        assertEquals("GET", endpoint.method)
        assertFalse(endpoint.bearerCapable)
        assertEquals(null, endpoint.contentType)
    }

    @Test
    fun `uses the same russian culture ordering as PowerShell`() {
        val javascript = """client.get({url:"/api/events/boss/config/"});client.get({url:"/api/events/boss/{id}/"})"""
        val paths = ApiExtractor.endpoints(listOf(BundleSource("a.js", javascript))).map { it.path }
        assertEquals(listOf("/api/events/boss/{id}/", "/api/events/boss/config/"), paths)
    }
}
