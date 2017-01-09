class UserController {
    constructor(userView, requester, baseUrl, appKey){
        this._userView = userView;
        this._requester = requester;
        this._appKey = appKey;
        this._baseServiceUrl = baseUrl + "/user/" + appKey + "/"; 
    }
    
    showLoginPage(){
        this._userView.showLoginPage();
    }
    
    showRegiterPage(){
        this._userView.showRegisterPage();
    }
    
    register(data){
        if (data.username.lenght <6){
            showPopup('error', 'Smaller username');
            return;
        }
        
        if (data.fullNmae.length < 8){
            showPopup('error','Fullname is smaller');
            return;
        }
        
        if (data.password != data.confirmPassword){
            showPopup('error','Password not mutch');
            return;
        }
        
        if (data.password.length <3){
            showPopup('error','Lentght password is small');
            return;
        }
        
        delete data ['confirmPassword'];
        this._requester.post(this._baseServiceUrl, data,
            function successCallback(response){
                showPopup('success','You are register');
                redirectUrl('#/login');
            },
            function errorCallback(response){
                showPopup('error','Error during registrstion')
            }
        );
    }
    
    login(data){
        
    }
    
    logout(){
        sessionStorage.clear();
        redirectUrl('#/ ');
    }
}