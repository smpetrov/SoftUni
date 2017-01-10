class HomeController{
    constructor(homeView, requester, baseUrl, appKey){
        this._homeView = homeView;
        this._requester = requester;
        this._appKey = appKey;
        this._baseServiceUrl = baseUrl + "/appdata/" + appKey +"/posts";
    }
    
    showGuestPage(){
        let _that = this;
        let recentPosts = [];
        let requestUrl = this._baseServiceUrl;
        
        //Веми данните/response/ от baseServiceUrl 
        //и ги дай на function при success
        this._requester.get(this._baseServiceUrl,
            function success(response){
                showPopup('success' , 'Vzeh dannite');
                
                let currentId =1;
                response.sort( function (elem1, elem2) {
                    let date1 = new Date(elem1._kmd.ect);
                    let date2 = new Date(elem2._kmd.ect);
                    return date2-date1;
                });
                for (let i=0; i<response.length && i<5; i++){
                    response[i].postId = currentId;
                    currentId++;
                    recentPosts.push(response[i]);
                }
                
                _that._homeView.showGuestPage(response, recentPosts);
            },
            function error(data){
                showPopup('error' , 'Error during load data');
            });
    }
    
    showUserPage(){
        let _that = this;
        let recentPosts = [];
        let requestUrl = this._baseServiceUrl;
        
        //Веми данните/response/ от baseServiceUrl 
        //и ги дай на function при success
        this._requester.get(this._baseServiceUrl,
            function success(response){
                showPopup('success' , 'Vzeh dannite');
                
                let currentId =1;
                response.sort( function (elem1, elem2) {
                    let date1 = new Date(elem1._kmd.ect);
                    let date2 = new Date(elem2._kmd.ect);
                    return date2-date1;
                });
                for (let i=0; i<response.length && i<5; i++){
                    response[i].postId = currentId;
                    currentId++;
                    recentPosts.push(response[i]);
                }
                
                _that._homeView.showUserPage(response, recentPosts);
            },
            function error(data){
                showPopup('error' , 'Error during load data');
            });
    }
}