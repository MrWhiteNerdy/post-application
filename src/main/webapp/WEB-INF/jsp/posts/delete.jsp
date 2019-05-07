<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <h1>Delete ${post.title}</h1>
    <p>Are you sure you want to delete this post?</p>
    <form action="/posts/${post.id}/delete" method="post">
        <a href="/posts/${post.id}" class="btn btn-secondary">Cancel</a>
        <button type="submit" class="btn btn-danger">Delete</button>
    </form>
</t:layout>
