function createPost(){
    //като направим заявка към kinvey то тя ще направи колоните ако ги няма
    
    let title = $('#title').val();
    let author = $('#author').val();
    let description = $('#description').val();
    
    let data = {
        title:title,
        author:author,
        description:description
    };
    
    let method = "POST";
    let baseUrl = "https://baas.kinvey.com/";
    let appKey = "kid_BJcAjKA7g";
    let requestUrl = baseUrl + "appdata/" + appKey + "/books";
    
    let headers = {};
    headers['Authorization'] = "Kinvey " + sessionStorage.getItem('authToken');
    headers['Content-Type'] = "application/json";
    
    let request = {
        method:method,
        url:requestUrl,
        headers:headers,
        data:JSON.stringify(data)
    };
    
    $.ajax(request);    
}
