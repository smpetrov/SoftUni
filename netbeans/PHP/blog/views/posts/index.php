<main>
    <table>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Content</th>
            <th>Date</th>
            <th>Author</th>
            <th>Actions</th>
        </tr>
    
        <?php foreach($this->posts as $post) :?>
            <tr>
                <td><?= htmlspecialchars($post['id'])?></td>
                <td><?= htmlspecialchars($post['title'])?></td>
                <td><?= cutLongText($post['content'])?></td>
                <td><?= htmlspecialchars($post['date'])?></td>
                <td><?= htmlspecialchars($post['full_name'])?></td>
                <td>
                    <h1><a href="<?=APP_ROOT?>/posts/edit/<?= 
                        htmlentities($post['id'])?>">[Edit]</a></h1>
                    <h1><a href="<?=APP_ROOT?>/posts/delete/<?= 
                        htmlentities($post['id'])?>">[Delete]</a></h1>
                </td>
            </tr>
        <?php endforeach ?>
            
        </table>
</main>
