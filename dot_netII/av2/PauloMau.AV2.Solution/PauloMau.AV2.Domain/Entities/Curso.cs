using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PauloMau.AV2.Domain.Entities
{
    public class Curso
    {
        [ScaffoldColumn(false)]
        [Bindable(false)]
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int CursoId { get; set; }

        [Required(ErrorMessage = "O nome do curso é obrigatório!!!")]
        [DisplayName("Nome")]
        [StringLength(100)]
        public string Nome { get; set; }

        [DisplayName("Código")]
        [StringLength(10)]
        public string Codigo { get; set; }

        [Required(ErrorMessage ="Os objetivos do curso são obrigatórios!!!")]
        [DisplayName("Objetivos")]
        [DataType(DataType.MultilineText)]
        [StringLength(200)]
        public string Objetivos { get; set; }

        [DisplayName("Descrição")]
        [DataType(DataType.MultilineText)]
        [StringLength(300)]
        public string Descricao { get; set; }

        [DisplayName("Programa")]
        public Programa Programa { get; set; }

    }
}
