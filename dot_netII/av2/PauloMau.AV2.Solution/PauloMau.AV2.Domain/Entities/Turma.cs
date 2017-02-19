using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PauloMau.AV2.Domain.Entities
{
    public class Turma
    {
        [ScaffoldColumn(false)]
        [Bindable(false)]
        [ReadOnly(true)]
        public int TurmaId { get; set; }

        [DisplayName("Código")]
        [ReadOnly(true)]
        [StringLength(15)]
        [Required]
        public string Codigo { get; set; }

        [Required(ErrorMessage ="Curso é obrigatório para turma!!!")]
        [DisplayName("Curso")]
        public Curso Curso { get; set; }

        [DisplayName("Professor")]
        public Professor Professor { get; set; }

        [Required(ErrorMessage ="Sala é obrigatório para turma!!!")]
        [DisplayName("Sala")]
        public Sala Sala { get; set; }

        [Required(ErrorMessage ="A hora de ínicio é obrigatória!!!")]
        [DataType(DataType.Time)]
        [DisplayName("Hora de início")]
        [DisplayFormat(DataFormatString ="{00:00:00}", ApplyFormatInEditMode =true)]
        public DateTime HorarioInicio { get; set; }

        [Required(ErrorMessage = "A hora de término é obrigatória!!!")]
        [DataType(DataType.Time)]
        [DisplayName("Hora de Término")]
        [DisplayFormat(DataFormatString = "{00:00:00}", ApplyFormatInEditMode = true)]
        public DateTime HorarioFim { get; set; }

        [Required(ErrorMessage = "A data de ínicio é obrigatória!!!")]
        [DataType(DataType.Date)]
        [DisplayName("Data de início")]
        [DisplayFormat(DataFormatString = "{0:dd-MM-yyyy}", ApplyFormatInEditMode = true)]
        public DateTime DataInicio { get; set; }

        [Required(ErrorMessage = "A data de término é obrigatória!!!")]
        [DataType(DataType.Date)]
        [DisplayName("Data de término")]
        [DisplayFormat(DataFormatString = "{0:dd-MM-yyyy}", ApplyFormatInEditMode = true)]
        public DateTime DataFim { get; set; }
    }
}
