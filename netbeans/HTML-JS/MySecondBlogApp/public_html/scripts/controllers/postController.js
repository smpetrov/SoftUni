class PostController{
    constructor (postView, requester, baseUrl, appKey){
        this._postView = postView;
        this._requester = requester;
        this._appKey = appKey;
        this._baseServiceUrl = baseUrl; //TODO
    }
    
    showCreatePostPage(){
        this._postView.showCreatePostPage();
    }
    
    createNewPost(data){
        
    }
}