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
                $this->addErrorMessage("Username invalid !");
                return;
            }
            if ($password != $password_confirm){
                $this->addErrorMessage("Passwords do not match !");
                return;
            }
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
}
