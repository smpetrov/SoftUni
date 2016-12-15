function login(){
    let username = $('#username').val();
    let password = $('#pass').val();
    
    let data={
        username:username,
        password:password
    };
    
    let baseUrl = "https://baas.kinvey.com/";
    let appKey = "kid_BJcAjKA7g";
    let appSecret = "0c247b4bdf8a4625a4de7cd6ace82822";
    
    let headers = {};
    headers['Authorization'] = "Basic " + btoa(appKey + ":" + appSecret);
    headers['Content-Type'] = "application/json";
    
    let method = "POST";
    let requestUrl = baseUrl + "user/" + appKey + "/login";
    
    let request = {
        method:method,
        url:requestUrl,
        headers:headers,
        data:JSON.stringify(data)
    };
    
    $.ajax(request).then(function(response, status){
        //user - aaa
        //pssword - 111
        //console.log(response);
        //console.log(status);
        
        //token - нещо като уникален индентификатор на сесията
        let userAuth = response._kmd.authtoken;
        console.log(userAuth);
        
        //всеки броузер подържа някакъв session storage 
        //във формат ключ-стойност
        sessionStorage.setItem('authToken',userAuth);
    });
    
}

function logout(){
    sessionStorage.clear();
}