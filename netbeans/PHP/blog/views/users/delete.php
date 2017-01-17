<?php $this->title = 'Delete User'; ?>

<h1><?= htmlspecialchars($this->title)?></h1>

<form method="post">
    <div>User:</div>
    <input type="text" name = "user_username" disabled 
           value="<?= htmlspecialchars($this->user['username'])?>"/>
    <div>Fullname:</div>
    <input type="text" name = "user_full_name" disabled 
           value="<?= htmlspecialchars($this->user['full_name'])?>"/>
    <div>
        <input type="submit" value="Delete"/>
        <a href="<?=APP_ROOT?>/posts">[Cancel]</a>
    </div>
</form>
