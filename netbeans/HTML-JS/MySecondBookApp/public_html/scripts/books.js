const kinveyAppID = 'kid_rymSxI9Ve';
const kinveyAppSecret = '5b4ec221f10f462ba41acb5096f7ebc6';
const kinveyServiceBaseUrl = 'https://baas.kinvey.com/';

function showView(viewId){
    $("main > section").hide();
    
    $("#"+viewId).show();
}

function showHideNavigationLinks(){
    let loggedIn = sessionStorage.authToken != null;
    if (loggedIn) {
        $("#linkLogin").hide();
        $("#linkRegister").hide();
        $("#linkListBooks").show();
        $("#linkCreateBook").show();
        $("#linkLogout").show();
    } else {
        $("#linkLogin").show();
        $("#linkRegister").show();
        $("#linkListBooks").hide();
        $("#linkCreateBook").hide();
        $("#linkLogout").hide();
    }
    
}

function showHomeView() {
    showView('viewHome');
}

function showLoginView() {
    showView('viewLogin');
}

function login(){
    let authBase64 = btoa(kinveyAppID + ":" +kinveyAppSecret);
    let loginUrl = kinveyServiceBaseUrl + "user/" + kinveyAppID + "/login";
    let loginData = {
                username: $("#loginUser").val(),
                password: $("#loginPass").val()
    };
    $.ajax({
            method: "POST",
            url: loginUrl,
            data: loginData,
            headers: {"Authorization": "Basic "+authBase64},
            success: loginSuccess,
            error: showAjaxError
        });
    function loginSuccess(data , status){
        sessionStorage.authToken=data._kmd.authtoken;
        showListBooksView();
        showHideNavigationLinks();
        showInfo("Login sucsessful !");
    }    
}

function showInfo(messageText){
    $("#infoBox").text(messageText).show().delay(3000).fadeOut();
}

function showAjaxError(data, status) {
    let errorMsg = ' ';
    if (typeof(data.readyState) !='undefined' && data.readyState ==0){
        errorMsg = "Network error !!!";
    } else if (data.responseJSON && data.responseJSON.description){
        errorMsg = data.responseJSON.description;
    } else {
        let errorMsg = "Error:" + JSON.stringify(data);
    } 
    $('#errorBox').text(errorMsg).show();
}

function showRegisterView() {
    showView('viewRegister');
}

function register() {
    let authBase64 = btoa(kinveyAppID + ":" +kinveyAppSecret);
    let loginUrl = kinveyServiceBaseUrl + "user/" + kinveyAppID + "/";
    let loginData = {
                username: $("#registerUser").val(),
                password: $("#registerPass").val()
    };
    $.ajax({
            method: "POST",
            url: loginUrl,
            data: loginData,
            headers: {"Authorization": "Basic "+authBase64},
            success: registerSuccess,
            error: showAjaxError
        });
    function registerSuccess(data , status){
        sessionStorage.authToken=data._kmd.authtoken;
        showListBooksView();
        showHideNavigationLinks();
        showInfo("User registered sucsessfully !");
    }
}

function makeComment(){
    let currentElement = document.activeElement;
    currentElement.style.display = 'none';
    currentElement.nextElementSibling.style.display='inline-block';
}    

function writeComment(){
     let parentSubmitElement = document.activeElement.parentNode.parentNode;
     
     let elements = parentSubmitElement.childNodes;
     let commentT = elements[0].childNodes[1].value;
     let authorT = elements[1].childNodes[1].value;
//     console.log(elements[4].childNodes[0].value);
     let bookT = JSON.parse(elements[4].childNodes[0].value);
    
     if (!bookT.comments){
         bookT.comments = [];
     }
    
     bookT.comments.push({author: authorT , commentText: commentT });
//     console.log(commentT);
//     console.log(authorT);
//     console.log(bookT._id);
     
    let booksUrl = kinveyServiceBaseUrl + "appdata/" + kinveyAppID + "/books";
    
    //тук може и без JSON
    $.ajax({
            method: "PUT",
            url: booksUrl+"/"+bookT._id,
            data:JSON.stringify(bookT),
            headers: {"Authorization": "Kinvey "+sessionStorage.authToken,
                "Content-Type":"application/json"},
            success: doComment,
            error: showAjaxError
        });
    function doComment(data){
        showListBooksView();
        showInfo("Write OK !");
    }    
}

function cancelComment(){
    let parentElement = document.activeElement.parentNode.parentNode;
    parentElement.style.display = 'none';
    parentElement.previousElementSibling.style.display='inline-block';
}

function showListBooksView() {
    showView('viewListBooks');
    $("#books").text('Loading ...');
    let authBase64 = btoa(kinveyAppID + ":" +kinveyAppSecret);
    let booksUrl = kinveyServiceBaseUrl + "appdata/" + kinveyAppID + "/books";
    $.ajax({
            method: "GET",
            url: booksUrl,
            headers: {"Authorization": "Kinvey "+sessionStorage.authToken},
            success: booksLoaded,
            error: showAjaxError
        });

    function booksLoaded(books , status){
        showInfo("Books loaded !");
        $("#books").text(' ');
        let booksTable = $("<table>")
            .append($("<tr>")
                .append($('<th>Title</th>'))
                .append($('<th>Auther</th>'))
                .append($('<th>Description</th>'))
        );
        for (let book of books) {
            let bookJSONString = JSON.stringify(book);
            booksTable.append($("<tr>")
                    .append($('<td></td>').text(book.title))
                    .append($('<td></td>').text(book.author))
                    .append($('<td></td>').text(book.description))
            );
            booksTable.append($("<tr>")
                      .append($('<td colspan="3">')));
            
            if (book.comments){
                if(book.comments.length > 0){
                    for (let comment of book.comments){
                       booksTable.append($('<div></div>').text(comment.commentText))
                                 .append($('<div class="authorComment"></div>').text("-- "+comment.author)
                        );
                    }
                }
            }   
                       
            booksTable.append($('<div>')
                      .append($('<a href="#" class="linkAddComment" onclick="makeComment();"></a>').text("Add comment"))  
                      .append($('<from class="addComment">')
                      .append($('<div class="elementComment">Comment <input type="text" id="commentTitle" required /></div>'))
                      .append($('<div class="elementComment">Author <input type="text" id="authorTitle" required /></div>'))
                      .append($('<div class="elementComment"><input type="submit" onclick="writeComment();" value="Add comment" /></div>'))
                      .append($('<div class="elementComment"><input type="submit" onclick="cancelComment();" value="Cancel" /></div>'))
                      .append($("<div class='elementComment'><input type='hidden' value='"+bookJSONString+"' /></div>"))
                      )
                      .append($('</form>'))
                      )
                      .append($('</div>'));
            
            booksTable.append($('</td></tr>'));
    
            booksTable.append($("</tr>"));
        }
        $("#books").append(booksTable);
        $(".addComment").hide();
    }    
    
}

function showCreateBookView() {
    showView('viewCreateBook');
}

function createBook() {
    let booksUrl = kinveyServiceBaseUrl + "appdata/" + kinveyAppID + "/books";
    let newBookData = {
        title:$("#bookTitle").val(),
        author:$("#bookAuthor").val(),
        description:$("#bookDescription").val()
    };
    //тук може и без JSON
    $.ajax({
            method: "POST",
            url: booksUrl,
            data:JSON.stringify(newBookData),
            headers: {"Authorization": "Kinvey "+sessionStorage.authToken,
                "Content-Type":"application/json"},
            success: bookCreated,
            error: showAjaxError
        });
    function bookCreated(data){
        showListBooksView();
        showInfo("Books created !");
    }    
}

function logout() {
    sessionStorage.clear();
    showHomeView();
    showHideNavigationLinks();
}

//$(нещо) - изпълнява се след като се зареди цялото dom дърво
$(function(){
    $("#linkHome").click(showHomeView);
    $("#linkLogin").click(showLoginView);
    $("#linkRegister").click(showRegisterView);
    $("#linkListBooks").click(showListBooksView);
    $("#linkCreateBook").click(showCreateBookView);
    $("#linkLogout").click(logout);
    
    //дали формата или логина са по бързи винаги става
    $("#formLogin").submit(function(e) {e.preventDefault(); login();});
    $("#formRegister").submit(function(e) {e.preventDefault(); register();});
    $("#formCreateBook").submit(function(e) {e.preventDefault(); createBook();});
    //$("#formLogout").click(logout);
    
    showHomeView();
    showHideNavigationLinks();
    
    $(document)
            .ajaxStart(function (){
                $("#loadingBox").show();
            })
                    .ajaxStop(function () {
                        $("#loadingBox").hide();
                    });
});