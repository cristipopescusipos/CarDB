<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page isELIgnored="false" %>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Owner add :</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
    <link rel="stylesheet" href="../../assets/css/Navigation-Clean.css">
    <link rel="stylesheet" href="../../assets/css/styles.css">
    <link rel="stylesheet" href="../../assets/css/Footer-Basic.css">
    <link  href="../../assets/css/Features-Boxed.css" rel="stylesheet" >
    <link  href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" rel="stylesheet" >
    <link  href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" >
    <link  href="../../assets/css/Features-Clean.css" rel="stylesheet" >
    <link  href="../../assets/css/Footer-Clean.css" rel="stylesheet" >

</head>
<body>
<%@include file="../includes/menu.jsp" %>

<div class="container p-3 mb-2 bg-light text-dark">

    <h1 class="text-primary"><i class="fa fa-user"></i>&nbsp;Add Owner :</h1>

    <form class="form-horizontal" method="post" action="/owner">

        <input type="hidden" name="action" value="add">
        <div class="form-group">
            <label class="col-sm-4 control-label">Input Owner Name :</label>
            <div class="col-sm-8">
                <input class="form-control" type="text" name="name">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label">Input Owner Telephone :</label>
            <div class="col-sm-8">
                <input class="form-control" type="text" name="telephone">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label">Input Owner Email :</label>
            <div class="col-sm-8">
                <input class="form-control" type="text" name="email">
            </div>
        </div>
        <button class="btn btn-primary" type="submit" value="ADD" >ADD</button>
    </form>

    <%@include file="../includes/footer.jsp" %>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
<script src="../../assets/js/bs-animation.js"></script>

</body>
</html>
