using PauloMau.AV2.Domain.Validators;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;

namespace PauloMau.AV2.Domain.Entities
{
    public class Programa
    {
        [ScaffoldColumn(false)]
        [Bindable(false)]
        [ReadOnly(true)]
        public int ProgramaId { get; set; }

        [Required(ErrorMessage = "O nome do programa é obrigatório!!!")]
        [DisplayName("Nome")]
        [StringLength(100)]
        public string Nome { get; set; }

        [DisplayName("Descrição")]
        [DataType(DataType.MultilineText)]
        [StringLength(300)]
        public string Descricao { get; set; }

        [DisplayName("Cursos")]
        [EnsureOneElementAttribute]
        public List<Curso> Cursos { get; set; }
    }
}