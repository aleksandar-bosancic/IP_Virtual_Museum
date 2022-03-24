<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="resources/style.css">
    <title>IP Bank</title>
</head>
<body>
<div class="login-container">
    <form class="form-container" method="post" action="login">
        <input type="text" class="form-control" id="holder_name" name="holder_name" placeholder="Card holder name" required>
        <input type="text" class="form-control" id="card_number" name="card_number" placeholder="Card number" required>
        <input type="password" class="form-control" id="pin" name="pin" placeholder="Pin" required>
        <button type="submit" class="btn btn-primary">Login</button>
    </form>
</div>
</body>
</html>