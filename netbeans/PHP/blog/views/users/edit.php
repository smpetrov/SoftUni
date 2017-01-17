<?php $this->title = "Edit Existing user"; ?>

<h1><?= htmlspecialchars($this->title)?></h1>

<form method="post">
    <div>User:</div>
    <input type=text" name="user_username" value="<?= 
        htmlspecialchars($this->user['username'])?>" />
    <div>Fullname:</div>
    <input type=text" name="user_full_name" value="<?= 
        htmlspecialchars($this->user['full_name'])?>" />
    <div><input type="submit" value="Edit" />
        <a href="<?=APP_ROOT?>/users">[Cancel]</a></div>
</form>