<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <a href="/posts" class="btn btn-secondary mb-3">Back</a>
    <h1>${post.title}</h1>
    <p class="text-muted">
        Last updated at
        <fmt:formatDate
                type="both"
                dateStyle="short"
                timeStyle="short"
                value="${post.modifyDate}"
        />
    </p>
    <p>${post.body}</p>
    <div>
        <a href="/posts/${post.id}/edit" class="btn btn-primary">Edit</a>
        <a href="/posts/${post.id}/delete" class="btn btn-danger">Delete</a>
    </div>
    <div>
        <hr>
        <c:if test="${not empty commentError}">
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    ${commentError}
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>
        <h4>Comments:</h4>
        <c:if test="${empty post.comments}">
            <p>No comments</p>
        </c:if>
        <ul class="list-group">
            <c:forEach items="${post.comments}" var="comment">
                <li class="list-group-item d-flex justify-content-between align-items-center">
                    <div class="d-flex flex-column">
                        <span>${comment.text}</span>
                        <span class="text-muted">
                            <fmt:formatDate
                                    type="both"
                                    dateStyle="short"
                                    timeStyle="short"
                                    value="${comment.createDate}"
                            />
                        </span>
                    </div>
                    <form action="/comments/delete" method="post">
                        <input type="hidden" name="postId" value="${post.id}">
                        <input type="hidden" name="commentId" value="${comment.id}">
                        <input type="submit" value="&times;" class="btn btn-danger">
                    </form>
                </li>
            </c:forEach>
        </ul>
        <form action="/comments" method="post" class="mt-4">
            <input type="hidden" name="postId" value="${post.id}"/>
            <div class="form-group">
                <textarea class="form-control" name="comment" placeholder="Add a comment..."></textarea>
            </div>
            <input type="submit" class="btn btn-primary" value="Submit"/>
        </form>
    </div>
</t:layout>
