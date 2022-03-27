function updateBlocked(id) {
    let switchButton = document.getElementById('switchAccountBlocked');
    let url = window.location.protocol + '//' + window.location.host + '/block?' + 'id=' + id + '&blocked=';
    url += (switchButton.checked)? 'true' : 'false';
    $.ajax({
        type: 'GET',
        url: url,
        data: JSON,
        async: true,
        error: function (){
            switchButton.checked = false;
            alert('Blocking was not successful, please try again');
        }
    })
}