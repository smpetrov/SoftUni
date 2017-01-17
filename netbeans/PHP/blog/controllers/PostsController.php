<?php

class PostsController extends BaseController
{
    function onInit()
    {
        $this->authorize();
    }
    
    function index() 
    {
        $this->posts = $this->model->getAll();
    }
    
    function create() 
    {
        if ($this->isPost){
            $title = $_POST['post_title'];
            if (strlen($title)<1){
                $this->setValidationError("post title", "Title is empty");
            }
            $content = $_POST['post_content'];
            if (strlen($content)<1){
                $this->setValidationError("post_content", "Content is empty");
            }
            if ($this->formValid()){
                $userId = $_SESSION['user_id'];
                if ($this->model->create($title, $content, $userId)){
                    $this->addInfoMessage("Post created.");
                    $this->redirect("posts");
                } else {
                    $this->addErrorMessage("Cannot create post.");
                }
            }
        }
    }
    
    function delete(int $id)
    {
        if ($this->isPost){
            //POST
            if ($this->model->delete($id)){
                $this->addInfoMessage("Post deleted !");
            } else {
                $this->addErrorMessage("Cannot delete post.");
            }
            $this->redirect('posts');
        } else {
            //GET
            $post = $this->model->getPostById($id);
            if(!$post){
                $this->addErrorMessage("Post does not exist !");
                $this->redirect("posts");
            }
            $this->post = $post;
        }
    }
    
    function edit(int $id)
    {
        if ($this->isPost){
            //POST
            $title = $_POST['post_title'];
            if (strlen($title) < 1){
                $this->setValidationError ("post_title", "Title is empty !");
            }
            $content = $_POST['post_content'];
            if (strlen($content) < 1){
                $this->setValidationError ("post_content", "Content is empty !");
            }
            $date = $_POST['post_date'];
            $dateRegex = '/^\d{2,4}-\d{1,2}-\d{1,2}( \d{1,2}:\d{1,2}(:\d{1,2})?)?$/';
            if (!preg_match($dateRegex, $date)){
                $this->setValidationError("post_date", "Invalid date !");
            }
            $user_id = $_POST['user_id'];
            if ($user_id <= 0 || $user_id > 1000000){
                $this->setValidationError("user_id", "Invalid author user ID !");
            }
            if ($this->formValid()){
                if ($this->model->edit($id, $title, $content, $date, $user_id)){
                    $this->addInfoMessage("Post edited !");
                } else {
                    $this->addErrorMessage("Cannot edit post !");
                }
                $this->redirect('posts');
            }
        }    //GET
        $post = $this->model->getPostById($id);
        if(!$post){
            $this->addErrorMessage("Post does not exist !");
            $this->redirect("posts");
        }
        $this->post = $post;
    }
    
}
