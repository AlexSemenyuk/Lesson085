<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <%@ page contentType="text/html; charset=UTF-8" %>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>Blog Home - Start Bootstrap Template</title>
    <%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"--%>
    <%--          rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"--%>
    <%--          cros-sorigin="anonymous">--%>
    <%--    <link rel="icon" type="image/x-icon" href="<c:url value="/resources/assets/favicon.ico"/>"/>--%>

<%--    <link href="<c:url value="/resources/css/styles.css"/>" rel="stylesheet"/>--%>
    <link href="${pageContext.request.contextPath}/static/css/styles.css" rel="stylesheet"/>
    <style>
        #title.input,
        #imagepath.input {
            margin-top: 30px;
        }

        .input {
            margin-top: 30px;
        }

        .ml-5 {
            margin-left: 20px;
        }

        #but-book.btn,
        #but-author.btn,
        #but-publisher.btn{
            display: block;
            margin-top: 30px;
            width: 100%;
            margin-bottom: 30px;
        }

        input#file-upload-button {
            background-color: #0d6efd;
            border-radius: 3px;
        }
        .sum-item {
            font-size: 24px;
        }
        li {
            list-style: none;
        }

    </style>
</head>
<body>
<main>
    <div class="container">
        <div class="row">
            <div class="col-xl-2"></div>
            <div class="col-xl-8 text-center my-5">
                <details>
                    <summary class="sum-item">Input your book</summary>
                    <form class="form" method="post">
                    <%-- title --%>
                    <input type="text" class="input form-control" id="title" name="title"
                           placeholder="Book title" required>
                    <%-- pages --%>
                    <input type="number" class="input form-control" id="pages" name="pages"
                               placeholder="Amount of Page" required>
                    <%-- author--%>
                    <input type="text" class="input form-control" id="author" name="author"
                           placeholder="Input your authors separated by commas" required>
                    <%-- publisher--%>
                    <input type="text" class="input form-control" id="publisher" name="publisher"
                           placeholder="Input your publishers separated by commas" required>
                    <button type="submit" class="btn btn-primary" id="but-book">Ok</button>
                </form>
                </details>
                <details>
                    <summary class="sum-item">Input your author</summary>
                    <form method="post">
                        <input type="text" class="input form-control" id="addauthor" name="addauthor"
                               placeholder="Input your authors separated by commas" required>
                        <button type="submit" class="btn btn-primary"  id="but-author">Ok</button>
                    </form>
                </details>
                <details>
                    <summary class="sum-item">Input your publisher</summary>
                    <form method="post">
                    <input type="text" class="input form-control" id="addpublisher" name="addpublisher"
                           placeholder="Publisher" required>
                    <button type="submit" class="btn btn-primary" id="but-publisher">Ok</button>
                    </form>
                </details>
                <h2>your books</h2>
                <table class="table out-books">
                    <thead>
                    <tr>
                        <th>Book id</th>
                        <th>Book title</th>
                        <th>Pages</th>
                        <th>Authors</th>
                        <th>Publishers</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${books}" var="book" varStatus="s">
                        <tr>
                            <th>${book.id}</th>
                            <th>${book.title}</th>
                            <th>${book.pages}</th>
                            <th>
                                <ul>
                                <c:forEach items="${book.authors}" var="author" varStatus="s">
                                    <li>${author.author}</li>
                                </c:forEach>
                                </ul>
                            </th>
                            <th>
                                <ul>
                                   <c:forEach items="${book.publishers}" var="publisher" varStatus="s">
                                      <li>${publisher.publisher}</li>
                                   </c:forEach>
                                </ul>
                            </th>
                            <th>
                                <a href="/Lesson085/${book.id}">Delete</a>
                            </th>
<%--                            <th>--%>
<%--                                <div class="form-check">--%>
<%--                                    <input class="radio-edit form-check-input" type="radio" value="${book.id}"--%>
<%--                                           id="flexCheckChecked" name="edit">--%>
<%--                                    <label class="form-check-label" for="flexCheckChecked">--%>
<%--                                        Edit--%>
<%--                                    </label>--%>
<%--                                </div>--%>
<%--                            </th>--%>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <h2>table authors</h2>
                <table class="table out-authors">
                    <thead>
                    <tr>
                        <th>Author id</th>
                        <th>Author</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${authors}" var="author" varStatus="s">
                        <tr>
                            <th>${author.id}</th>
                            <th>${author.author}</th>
                            <th>
                                <a href="/Lesson085/author/${author.id}">Delete</a>
                            </th>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <h2>table publishers</h2>
                <table class="table out-publishers">
                    <thead>
                    <tr>
                        <th>Publisher id</th>
                        <th>Publisher</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${publishers}" var="publisher" varStatus="s">
                        <tr>
                            <th>${publisher.id}</th>
                            <th>${publisher.publisher}</th>
                            <th>
                                <a href="/Lesson085/publisher/${publisher.id}">Delete</a>
                            </th>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <form method="post">
                    <div class="find-total">
                        <label for="find">For Find</label>
                        <select id="find" name="find">
                            <option value="title">By Book Title</option>
                            <option value="author">By Author</option>
                            <option value="publisher">By Publisher</option>
                        </select>
                        <input type="text" class="input form-control d-inline-block w-50" id="finddata" name="finddata"
                               placeholder="Find Data">
                        <form class="d-inline" >  <%--action="<c:url value="/"></c:url>"--%>
                            <button class="btn btn-primary d-inline" type="submit">Confirm Edit</button>
                        </form>
                    </div>
                </form>
                <c:choose>
                    <c:when test="${find != null}">
                        <h2>Yours book</h2>
                        <table class="table out-books">
                            <thead>
                            <tr>
                                <th>Book id</th>
                                <th>Book title</th>
                                <th>Pages</th>
                                <th>Authors</th>
                                <th>Publishers</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${find}" var="book" varStatus="s">
                                <tr>
                                    <th>${book.id}</th>
                                    <th>${book.title}</th>
                                    <th>${book.pages}</th>
                                    <th>
                                        <ul>
                                            <c:forEach items="${book.authors}" var="author" varStatus="s">
                                                <li>${author.author}</li>
                                            </c:forEach>
                                        </ul>
                                    </th>
                                    <th>
                                        <ul>
                                            <c:forEach items="${book.publishers}" var="publisher" varStatus="s">
                                                <li>${publisher.publisher}</li>
                                            </c:forEach>
                                        </ul>
                                    </th>
                                    <th>
                                        <a href="/Lesson085/${book.id}">Delete</a>
                                    </th>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <div>Book wasn't found</div>
                    </c:otherwise>

                </c:choose>
            </div>
            <div class="col-xl-2"></div>
        </div>
    </div>


</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        in-tegrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        cros-sorigin="anonymous"></script>
<script src="https://cdn.tiny.cloud/1/smgt7motjmt39brlvr481ll1m7kn9fycui3egjbpp7vdwodp/tinymce/6/tinymce.min.js"
        referrerpolicy="origin"></script>
<script>
    tinymce.init({
        selector: '#description'
    });
</script>

<script>
    <%--function getAttributeFromTarget(target) {--%>
    <%--    const postId = target.getAttribute('data-id-delete');--%>
    <%--    fetch("<c:url value="/"/>", {--%>
    <%--        method: "POST",--%>
    <%--        headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},--%>
    <%--        body: `id=\${postId}`--%>
    <%--    }).then(response => {--%>
    <%--        console.log(response.status);--%>
    <%--        location.reload();--%>
    <%--        // if (response.redirected) {--%>
    <%--        //     location = '/';--%>
    <%--        // }--%>
    <%--    }).catch(error => {--%>
    <%--        alert(error);--%>
    <%--    });--%>
    <%--}--%>

    <%--const tableOutBooks = document.querySelector(".out-books");--%>
    <%--tableOutBooks.addEventListener("click", e => {--%>
    <%--    if (e.target.nodeName === 'BUTTON') {--%>
    <%--        const target1 = e.target;--%>
    <%--        getAttributeFromTarget(target1);--%>
    <%--    }--%>
    <%--});--%>


    <%--let buttonEdit = document.getElementById("btn-edit");--%>
    <%--console.dir(buttonEdit);--%>
    <%--buttonEdit.addEventListener('click', () => {--%>
    <%--    console.log("super 1");--%>
    <%--    let radio = document.querySelectorAll(".radio-edit");--%>
    <%--    let editId = "1000";--%>
    <%--    for (let i = 0; i < radio.length; i++) {--%>
    <%--        console.dir(radio[i]);--%>
    <%--        if (radio[i].checked) {--%>
    <%--            editId = radio[i].value;--%>
    <%--            console.log("super 2");--%>
    <%--            // dataId = radio[i].getAttribute('data-id-edit');--%>
    <%--            break;--%>
    <%--        }--%>
    <%--    }--%>
    <%--    console.log("editId" + editId);--%>
    <%--    &lt;%&ndash;}&ndash;%&gt;--%>
    <%--    &lt;%&ndash;// console.log("data = " + data);&ndash;%&gt;--%>
    <%--    fetch("<c:url value="/"/>", {--%>
    <%--        method: "POST",--%>
    <%--        headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},--%>
    <%--        body: `editId=\${editId}`--%>
    <%--    }).then(response => {--%>
    <%--        console.log(response.status);--%>
    <%--        location.reload();--%>
    <%--        // if (response.redirected) {--%>
    <%--        //     location = '/';--%>
    <%--        // }--%>
    <%--    }).catch(error => {--%>
    <%--        alert(error);--%>
    <%--    });--%>
    <%--});--%>
</script>
</body>
</html>