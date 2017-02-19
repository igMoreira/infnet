using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PauloMau.AV2.Domain.Entities
{
    public class CursoProfessor
    {
        [ScaffoldColumn(false)]
        [Bindable(false)]
        [ReadOnly(true)]
        public int CursoProfessorId { get; set; }

        [Required(ErrorMessage = "O curso deve ser selecionado!!!")]
        public Curso Curso { get; set; }

        [Required(ErrorMessage ="O professor deve ser selecionado!!!")]
        public Professor Professor { get; set; }

        [Required]
        [DisplayName("Ativo")]
        public bool Ativo { get; set; }
    }
}
