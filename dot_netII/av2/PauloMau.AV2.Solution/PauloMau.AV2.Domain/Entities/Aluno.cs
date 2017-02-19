using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations.Schema;

namespace PauloMau.AV2.Domain.Entities
{
    public class Aluno
    {
        [ScaffoldColumn(false)]
        [Bindable(false)]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        [Key]
        public int AlunoId { get; set; }

        [DisplayName("Matrícula")]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public Guid Matricula { get; set; }

        [Required(ErrorMessage ="O nome do aluno é obrigatório!!!")]
        [DisplayName("Nome")]
        [StringLength(100)]
        public string Nome { get; set; }

        [Required(ErrorMessage ="O CPF do aluno é obrigatório!!!")]
        [DisplayName("CPF")]
        [RegularExpression(@"^\d{3}\.\d{3}\.\d{3}-\d{2}$", ErrorMessage ="O CPF digitado é inválido!!!")]
        public string CPF { get; set; }

        [Required(ErrorMessage = "O email do aluno é obrigatório!!!")]
        [DisplayName("Email")]
        [StringLength(100)]
        public string Email { get; set; }

        [Required(ErrorMessage ="A data de nascimento é obrigatória!!!")]
        [DisplayName("Data de Nascimento")]
        [DataType(DataType.Date)]
        [DisplayFormat(DataFormatString = "{0:dd-MM-yyyy}", ApplyFormatInEditMode = false)]
        [Range(typeof(DateTime), "1/1/1900", "1/1/2000", ErrorMessage ="O aluno deve ser maior de 16 anos!!!")]
        public DateTime Nascimento { get; set; }

        [DisplayName("Data de Cadastro")]
        [DataType(DataType.DateTime)]
        [DisplayFormat(DataFormatString = "{0:dd-MM-yyyy HH:mm:ss}", ApplyFormatInEditMode = true)]
        public DateTime Cadastro { get; set; }

        [Required(ErrorMessage ="O nome da mãe é obrigatório!!!")]
        [StringLength(100)]
        [DisplayName("Nome da mãe")]
        public string Mae { get; set; }

        [Required(ErrorMessage ="O endereço é obrigatório!!!")]
        public Endereco Endereco { get; set; }

    }
}
