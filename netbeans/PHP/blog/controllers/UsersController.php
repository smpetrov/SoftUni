<?php

class UsersController extends BaseController
{
    public function register()
    {
        if ($this->isPost){
            $username = $_POST['username'];
            $password = $_POST['password'];
            $password_confirm = $_POST['password_confirm'];
            $full_name = $_POST['full_name'];
            if ($username == ''){
                //$this->addErrorMessage("Username invalid !");
                //return;
                $this->setValidationError("username", "Username invalid !");
            }
            if ($password != $password_confirm){
                //$this->setValidationError("Passwords do not match !");
                //return;
                $this->setValidationError("password_confirm","Passwords do not match !");
            }
            if ($this->formValid()){
                $userId = $this->model->register($username, $password, $full_name);
                if ($userId !== false){
                    $_SESSION['username'] = $username;
                    $_SESSION['user_id'] = $userId;
                    $this->addInfoMessage("Registration successfull !");
                    $this->redirect("");
                } else { 
                    $this->addErrorMessage("Registration failed !"); 
                }
            }    
        }
    }

    public function login()
    {
        if ($this->isPost){
            $username = $_POST['username'];
            $password = $_POST['password'];
            $userId = $this->model->login($username, $password);
            if ($userId !== false){
                $_SESSION['username'] = $username;
                $_SESSION['user_id'] = $userId;
                $this->addInfoMessage("Login successfull !");
                $this->redirect("");
            } else { 
                $this->addErrorMessage("Login failed !"); 
            }
        }
    }

    public function logout()
    {
        session_destroy();
        $this->redirect("");
    }
    
    function index() 
    {
        $this->users = $this->model->getAll();
    }
    
    function delete(int $id)
    {
        if ($this->isPost){
            //POST
            if ($this->model->delete($id)){
                $this->addInfoMessage("User deleted !");
            } else {
                $this->addErrorMessage("Cannot delete user.");
            }
            $this->redirect('users');
        } else {
            //GET
            $user = $this->model->getUserById($id);
            if(!$user){
                $this->addErrorMessage("User does not exist !");
                $this->redirect("users");
            }
            $this->user = $user;
        }
    }
    
    function edit(int $id)
    {
        if ($this->isPost){
            //POST
            $username = $_POST['user_username'];
            if (strlen($username) < 1){
                $this->setValidationError ("user_username", "Username is empty !");
            }
            $full_name = $_POST['user_full_name'];
            if (strlen($username) < 1){
                $this->setValidationError ("user_full_name", "Fullname is empty !");
            }
            if ($this->formValid()){
                if ($this->model->edit($id, $username, $full_name)){
                    $this->addInfoMessage("User edited !");
                } else {
                    $this->addErrorMessage("Cannot edit user !");
                }
                $this->redirect('users');
            }
        }    //GET
        $user = $this->model->getUserById($id);
        if(!$user){
            $this->addErrorMessage("User does not exist !");
            $this->redirect("user");
        }
        $this->user = $user;
    }
}
