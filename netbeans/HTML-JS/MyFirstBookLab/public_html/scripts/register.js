function register(){
    let username = $('#username').val();
    let password = $('#pass').val();
    
    let data = {
        username:username,
        password:password
    };
    
    let baseUrl = "https://baas.kinvey.com/";
    let appKey = "kid_BJcAjKA7g";
    let appSecret = "0c247b4bdf8a4625a4de7cd6ace82822";
    
    let method = "POST";
    let requestUrl = baseUrl + "user/" + appKey + "/";
    
    let headers = {};
    headers['Authorization'] = "Basic " + btoa(appKey + ":" + appSecret);
    headers['Content-Type'] = "application/json";
    
    let request = {
        method:method,
        url:requestUrl,
        headers:headers,
        data:JSON.stringify(data)
    };
    
    $.ajax(request).then(function(response, status){
        console.log(response);
        console.log(status);
    });
}
    
    