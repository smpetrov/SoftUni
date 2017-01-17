<main>
    <table>
        <tr>
            <th>ID</th>
            <th>UserName</th>
        </tr>
    
        <?php foreach($this->users as $user) :?>
            <tr>
                <td><?= htmlspecialchars($user['id'])?></td>
                <td><?= htmlspecialchars($user['username'])?></td>
                <td><?= htmlspecialchars($user['full_name'])?></td>
                <td>
                    <h1><a href="<?=APP_ROOT?>/users/edit/<?= 
                        htmlentities($user['id'])?>">[Edit]</a></h1>
                    <h1><a href="<?=APP_ROOT?>/users/delete/<?= 
                        htmlentities($user['id'])?>">[Delete]</a></h1>
                </td>
            </tr>
        <?php endforeach ?>
            
        </table>
</main>
