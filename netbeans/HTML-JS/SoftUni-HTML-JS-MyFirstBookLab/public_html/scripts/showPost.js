function showPost(){
    let method = "GET";
    let baseUrl = "https://baas.kinvey.com/";
    let appKey = "kid_BJcAjKA7g";
    let requestUrl = baseUrl + "appdata/" + appKey + "/books";
    
    let headers = {};
    headers['Authorization'] = "Kinvey " + sessionStorage.getItem('authToken');
    
    let request = {
        method:method,
        url:requestUrl,
        headers:headers
    };
    
    $.ajax(request).then(function (response){
       //console.log(response);
       for (let obj of response){
           //console.log(obj.title);
           let list = $('#booksList');
           let orderedListItem = document.createElement('li');
           
           let innerList = document.createElement('ul');
           
           let title = document.createElement('li');
           let author = document.createElement('li');
           let description = document.createElement('li');
           title.className="title-style";
           author.className="author-style";
           description.className="description-style";
           
           title.appendChild(document.createTextNode(obj.title));
           author.appendChild(document.createTextNode(obj.author));
           description.appendChild(document.createTextNode(obj.description));
           
           innerList.appendChild(title);
           innerList.appendChild(author);
           innerList.appendChild(description);
           
            orderedListItem.appendChild(innerList);
           
           list.append(orderedListItem);
       }
    });
}   
