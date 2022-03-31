<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <title>Welcome</title>
</head>
<body style="background-color: #e3f2fd">
<div style="display: flex; flex-direction: column; align-content: center; justify-content: center; height: 100vh">
    <form method="post" action="login" style="display: flex; flex-direction: column; align-items: center; height: 90vh; justify-content: center">
        <div class="mb-3">
            <input type="text" class="form-control" id="username" name="username" placeholder="Username">
        </div>
        <div class="mb-3">
            <input type="password" class="form-control" id="password" name="password" placeholder="Password">
        </div>
        <input type="submit" class="btn btn-primary" name="submit" value="Login"/>
    </form>
</div>
</body>
</html>
