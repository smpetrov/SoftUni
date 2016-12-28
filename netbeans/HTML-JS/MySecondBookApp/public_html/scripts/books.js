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
            booksTable.append($("<tr>")
                    .append($('<td></td>').text(book.title))
                    .append($('<td></td>').text(book.author))
                    .append($('<td></th>').text(book.description))
            );
            
        }
        $("#books").append(booksTable);
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