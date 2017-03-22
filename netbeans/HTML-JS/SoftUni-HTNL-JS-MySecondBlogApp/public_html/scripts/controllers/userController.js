class UserController {
    constructor(userView, requester, baseUrl, appKey){
        this._userView = userView;
        this._requester = requester;
        this._appKey = appKey;
        this._baseServiceUrl = baseUrl + "/user/" + appKey + "/"; 
    }
    
    showLoginPage(isLoggedIn){
        this._userView.showLoginPage(isLoggedIn);
    }
    
    showRegiterPage(isLoggedIn){
        this._userView.showRegisterPage(isLoggedIn);
    }
    
    register(data){
        if (data.username.length <3){
            showPopup('error', 'Smaller username');
            return;
        }
        
        if (data.fullname.length < 3){
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
        let requestUrl = this._baseServiceUrl + "login";
        this._requester.post(requestUrl, data, 
            function successCallback(response){
                //запазваме user-a  в sessionStorage за да може 
                //getCurrentUser да го чете от там
                sessionStorage.setItem('username' , response.username);
                sessionStorage.setItem('_authToken' , response._kmd.authtoken);
                sessionStorage.setItem('fullName' , response.fullname);
                
                showPopup('success' , 'Login success');
                redirectUrl('#/');
            },
            function errorCallback(response){
                showPopup('error' , 'Not login');
            }
        );
    }
    
    logout(){
        sessionStorage.clear();
        redirectUrl('#/');
    }
}