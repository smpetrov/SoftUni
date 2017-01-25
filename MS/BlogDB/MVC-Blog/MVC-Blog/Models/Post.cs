using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace MVC_Blog.Models
{
    public class Post
    {
        public Post()
        {
            this.Date = DateTime.Now;
        }

        [Key]
        public int Id { get; set; }

        [Required]
        [StringLength(250)]
        public String Title { get; set; }

        [Required]
        [DataType(DataType.MultilineText)]
        public String Body { get; set; }

        [Required]
        public DateTime Date { get; set; }

        public string Author_id { get; set; }
        [ForeignKey("Author_id")]
        public ApplicationUser Author { get; set; }

        public ICollection<Comment> Comments { get; set; }

    }
}