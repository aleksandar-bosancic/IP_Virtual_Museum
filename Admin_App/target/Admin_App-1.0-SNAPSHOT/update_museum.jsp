<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="museumBean" class="com.museum.admin.Admin_App.beans.MuseumBean" scope="request"/>
<jsp:useBean id="museumService" class="com.museum.admin.Admin_App.services.MuseumService" scope="application"/>
<!DOCTYPE html>
<%
    museumBean = museumService.findById(Integer.parseInt(request.getParameter("id")));
    if(request.getParameter("submit") != null){
        if("".equals(museumBean.getName()) || "".equals(museumBean.getAddress())
                || "".equals(museumBean.getPhoneNumber()) || "".equals(museumBean.getCountry())
                || "".equals(museumBean.getCity()) || "".equals(museumBean.getType())
                || museumBean.getLatitude() == 0.0 || museumBean.getLongitude() == 0.0){ %>
<script type="text/javascript">
    alert("All fields must be filled!");
</script>
<% } else {
    museumBean.setName(request.getParameter("name"));
    museumBean.setAddress(request.getParameter("address"));
    museumBean.setPhoneNumber(request.getParameter("phone_number"));
    museumBean.setType(request.getParameter("type"));
    museumBean.setCountry(request.getParameter("country"));
    museumBean.setCity(request.getParameter("city"));
    museumBean.setLatitude(Float.parseFloat(request.getParameter("latitude")));
    museumBean.setLongitude(Float.parseFloat(request.getParameter("longitude")));
    museumService.updateById(Integer.parseInt(request.getParameter("id")), museumBean);
    response.sendRedirect("index.jsp");
}
} %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <title>Add Museum</title>
</head>
<body onload="initMap()" style="background-color: #e3f2fd">
<script>
    $(document).ready(function () {
        $.ajax({
            type: 'GET',
            url: 'https://restcountries.com/v3.1/region/europe',
            data: JSON,
            async: true,
            success: function (data) {
                let countries = data.map(country => country.name.common);
                let codes = data.map(code => code.cca2);
                $.each(countries, function (index, value){
                    $('#country').append('<option id="' + codes[index] + '">' + value + '</option>');
                })
            }
        });
    });
    function getCities(){
        $('#city').empty();
        let element = document.getElementById("country");
        let countryCode = element.options[element.selectedIndex].id;
        $.ajax({
            type: 'GET',
            url: "http://battuta.medunes.net/api/region/" + countryCode + "/all/?key=97db76fcacfca6c4dfa700e7cc195188",
            dataType: 'jsonp',
            async: true,
            crossDomain: true,
            success: function (data){
                let regions = data.map(region => region.region);
                $.each(regions, function (index, value){
                    $.ajax({
                        type: 'GET',
                        url: "http://battuta.medunes.net/api/city/" + countryCode +
                            "/search/?region=" + value + "&key=97db76fcacfca6c4dfa700e7cc195188",
                        dataType: 'jsonp',
                        async: true,
                        crossDomain: true,
                        success: function (data){
                            let cities = data.map(city => city.city);
                            $.each(cities, function (index, value){
                                $('#city').append('<option value="' + value + '">' + value + '</option>');
                            })
                        }
                    })
                })
            }
        });
    }
</script>

<section class="container-fluid" style="height: 100vh; display: flex; align-items: center; justify-items: center; justify-content: center; align-content: center">
    <form method="post" style="display: flex; flex-direction: column; align-items: center">
        <div style="display: flex; justify-content: space-around">
            <div class="mb-5" style="width: 50vh; margin-right: 5vh;">
                <div class="mb-3">
                    <input type="text" class="form-control" id="name" name="name" placeholder="Name" value="<%=museumBean.getName()%>">
                </div>
                <div class="mb-3">
                    <input type="text" class="form-control" id="address" name="address" placeholder="Address" value="<%=museumBean.getAddress()%>">
                </div>
                <div class="mb-3">
                    <input type="text" class="form-control" id="phone_number" name="phone_number" placeholder="Phone Number" value="<%=museumBean.getPhoneNumber()%>">
                </div>
                <div class="mb-3">
                    <input type="text" class="form-control" id="type" name="type" placeholder="Type" value="<%=museumBean.getType()%>">
                </div>
            </div>
            <div class="mb-5" style="width: 50vh; margin-left: 5vh">
                <div class="mb-3">
                    <select class="form-select" id="country" name="country" onchange="getCities()">
                        <option selected><%=museumBean.getCountry()%></option>
                    </select>
                </div>
                <div class="mb-3">
                    <select class="form-select" id="city" name="city">
                        <option selected><%=museumBean.getCity()%></option>
                    </select>
                </div>
                <div class="input-group mb-3">
                    <input id="latitude" name="latitude" type="text" class="form-control" placeholder="Latitude" readonly value="<%=museumBean.getLatitude()%>">
                    <input id="longitude" name="longitude" type="text" class="form-control" placeholder="Longitude" readonly value="<%=museumBean.getLongitude()%>">
                    <button class="btn btn-outline-secondary" type="button" id="locationButton" data-bs-toggle="modal" data-bs-target="#modal">Select Location</button>
                    <div class="modal fade" id="modal" role="dialog">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title">Select Museum Location</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                                </div>
                                <div class="modal-body">
                                    <div id="map" style="height: 400px; width: 100%"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button type="submit" class="btn btn-primary w-25" name="submit">Update Museum</button>
    </form>
</section>
<script>
    function initMap() {
        let marker = null;
        let infoWindow = null;
        let latitude = document.getElementById("latitude").value;
        let longitude = document.getElementById("longitude").value;
        const currentLocation = { lat: Number(latitude), lng: Number(longitude) };

        const map = new google.maps.Map(document.getElementById("map"), {
            zoom: 6,
            center: currentLocation,
        });

        marker = new google.maps.Marker({
            position: currentLocation,
            map: map,
        })

        // Official documentation example is like this, why is it depricated?????????????????
        google.maps.event.addListener(map, 'click', function(event) {
            placeMarker(map, event.latLng);
        });

        function placeMarker(map, location) {
            document.getElementById("latitude").value = location.lat();
            document.getElementById("longitude").value = location.lng();
            if(marker == null) {
                marker = new google.maps.Marker({
                    position: location,
                    map: map
                });
            } else {
                marker.setPosition(location);
            }
            if(infoWindow == null) {
                infoWindow = new google.maps.InfoWindow({
                    content: 'Latitude: ' + location.lat() +
                        '<br>Longitude: ' + location.lng()
                });
                infoWindow.open(map, marker);
            } else {
                infoWindow.setContent('Latitude: ' + location.lat() +
                    '<br>Longitude: ' + location.lng());
                infoWindow.open(map,marker);
            }
        }
    }
</script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB7cyJ9CfxulAQOO0Ak7Rk1VVzJ-3vFOmk"></script>
</body>
</html>
