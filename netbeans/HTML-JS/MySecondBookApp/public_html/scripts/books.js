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
    let errorMsg = "Error:" + JSON.stringify(data);
    $('#errorBox').text(errorMsg).show();
}

function showRegisterView() {
    showView('viewRegister');
}

function register() {
    
}

function showListBooksView() {
    showView('viewListBooks');
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
    
    $("#buttonLogin").click(login);
    $("#buttonRegister").click(register);
    $("#buttonCreateBook").click(createBook);
    $("#buttonLogout").click(logout);
    
    showHomeView();
    showHideNavigationLinks();
})