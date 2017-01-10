class HomeView{
    constructor (mainContentSelector, wrapperSelector){
        this._mainContentSelector = mainContentSelector;
        this._wrapperSelector = wrapperSelector;
    }
    
    showGuestPage(mainData, sidebarData){
        let _that = this;
        //get функция на jquery за четене на templates
        //get връща template който се подава на function
        $.get('templates/welcome-guest.html',function(template){
            
            //mustache чрез template рендва sidebarData
            //т.е. покажи sidebarData данните чрез html 
            //темплейта template
            let renderedTemplate = Mustache.render(template,null);
            //console.log(renderedTemplate);
            $(_that._wrapperSelector).html(renderedTemplate);
            
            $.get('templates/posts.html',function(template){
                let blogPosts={
                  blogPosts:mainData  
                };
                
                let renderedPosts = Mustache.render(template, blogPosts);
                $('.articles').html(renderedPosts);
            });
            
            $.get('templates/recent-posts.html',function(template){
                let recentPosts={
                  recentPosts:sidebarData  
                };
                
                let renderedRecentPosts = Mustache.render(template, recentPosts);
                $('.recent-posts').html(renderedRecentPosts);
            });
        });
    }
    
    showUserPage(mainData, sidebarData){
        let _that = this;
        //get функция на jquery за четене на templates
        //get връща template който се подава на function
        $.get('templates/welcome-user.html',function(template){
            
            //mustache чрез template рендва sidebarData
            //т.е. покажи sidebarData данните чрез html 
            //темплейта template
            let renderedTemplate = Mustache.render(template,null);
            //console.log(renderedTemplate);
            $(_that._wrapperSelector).html(renderedTemplate);
            
            $.get('templates/posts.html',function(template){
                let blogPosts={
                  blogPosts:mainData  
                };
                
                let renderedPosts = Mustache.render(template, blogPosts);
                $('.articles').html(renderedPosts);
            });
            
            $.get('templates/recent-posts.html',function(template){
                let recentPosts={
                  recentPosts:sidebarData  
                };
                
                let renderedRecentPosts = Mustache.render(template, recentPosts);
                $('.recent-posts').html(renderedRecentPosts);
            });
        });
    }
}