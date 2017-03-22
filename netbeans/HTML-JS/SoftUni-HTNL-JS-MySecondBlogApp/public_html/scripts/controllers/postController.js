class PostController{
    constructor (postView, requester, baseUrl, appKey){
        this._postView = postView;
        this._requester = requester;
        this._appKey = appKey;
        this._baseServiceUrl = baseUrl + "/appdata/" + appKey + "/posts";
    }
    
    showCreatePostPage(fullName, isLoggedIn){
        this._postView.showCreatePostPage(fullName, isLoggedIn);
    }
    
    createNewPost(data){
        //TODO Validation
        this._requester.post(this._baseServiceUrl, data,
            function (responseData){
                showPopup('success', 'Write a post');
                redirectUrl("#/");
            },
            function (responseData){
                showPopup('error', 'Error during write post');
            }
        );
    }
}