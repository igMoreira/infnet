using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PauloMau.AV2.Domain.Entities
{
    public class TurmaAluno
    {
        [ScaffoldColumn(false)]
        [Bindable(false)]
        [ReadOnly(true)]
        public int TurmaAlunoId { get; set; }

        [Required(ErrorMessage ="A turma deve ser informada!!!")]
        [DisplayName("Turma")]
        public Turma Turma { get; set; }

        [Required(ErrorMessage ="O aluno deve ser informado!!!")]
        [DisplayName("Aluno")]
        public Aluno Aluno { get; set; }

        [DisplayName("Data de matrícula")]
        [DataType(DataType.DateTime)]
        [DisplayFormat(DataFormatString = "{00:00:00:dd-MM-yyyy}", ApplyFormatInEditMode = true)]
        [ReadOnly(true)]
        public DateTime DataMatricula { get; set; }

        [DisplayName("Nota")]
        public int Nota { get; set; }

    }
}
