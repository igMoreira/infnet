using System;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;

namespace IagoMoreira.ProjetoDDD.Application.ViewModel
{
    public class AlunoViewModel
    {
        [Key]
        [ScaffoldColumn(false)]
        public int AlunoId { get; set; }

        [DisplayName("Matrícula")]
        public Guid Matricula { get; set; }

        [Required(ErrorMessage = "O nome do aluno é obrigatório!!!")]
        [DisplayName("Nome")]
        [StringLength(100)]
        public string Nome { get; set; }

        [Required(ErrorMessage = "A data de nascimento é obrigatória!!!")]
        [DisplayName("Data de Nascimento")]
        [DataType(DataType.Date)]
        [DisplayFormat(DataFormatString = "{0:dd-MM-yyyy}", ApplyFormatInEditMode = false)]
        public DateTime DataNascimento { get; set; }

        [DisplayName("Data de Cadastro")]
        [DataType(DataType.DateTime)]
        [DisplayFormat(DataFormatString = "{0:dd-MM-yyyy HH:mm:ss}", ApplyFormatInEditMode = true)]
        public DateTime DataCriação { get; set; }

        [Required(ErrorMessage = "O CPF do aluno é obrigatório!!!")]
        [DisplayName("CPF")]
        [MinLength(11)]
        [MaxLength(11)]
        public string CPF { get; set; }
    }
}
