<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Calculator</title>

    <style>
        * {
            font-size: 30px;
            text-align: center;
            margin: 30px;
            line-height: 80px;
        }

        h1 {
            font-size: 50px;
            color: greenyellow;
        }
        select {
            color: cyan;
        }
    </style>
</head>

<body>
<h1>Простые математические вычисления</h1>

<form action="calculator" method="post">

    <div>
        <input style="color: red"  name="number1">

        <select name="operation">
            <option>Выберите операцию</option>
            <option value="sumOperation">+</option>
            <option value="substrOperation">-</option>
            <option value="multiplyOperation">&#215</option>
            <option value="divOperation">&#247</option>
        </select>

        <input style="color: darkorange" name="number2">
        <input type="submit" value="="/>
        <span style="color: deeppink">${result}</span>
    </div>

</form>
</body>
</html>