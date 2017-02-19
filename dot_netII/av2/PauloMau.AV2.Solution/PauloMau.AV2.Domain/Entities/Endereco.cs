using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace PauloMau.AV2.Domain.Entities
{
    public class Endereco
    {
        [ScaffoldColumn(false)]
        [Bindable(false)]
        [ReadOnly(true)]
        public int EnderecoId { get; set; }

        [Required(ErrorMessage ="O tipo de logradouro é obrigatório!!!")]
        [DisplayName("Tipo de logradouro")]
        [StringLength(15)]
        public string TipoLogradouro { get; set; }

        [Required(ErrorMessage ="A rua é obrigatória!!!")]
        [DisplayName("Rua")]
        [StringLength(150)]
        public string Rua { get; set; }

        [Required(ErrorMessage ="O número do logradouro é obrigatório!!!")]
        [DisplayName("Número")]
        [Range(1,9999)]
        public int Numero { get; set; }
        
        [DisplayName("Complemento")]
        [StringLength(100)]
        public string Complemento { get; set; }

        [Required(ErrorMessage ="O CEP é obrigatório!!!")]
        [RegularExpression(@"^\d{5}-\d{3}$", ErrorMessage ="O CEP digitado é inválido!!!")]
        [DisplayName("CEP")]
        public string CEP { get; set; }

        [Required(ErrorMessage ="O bairro é obrigatório!!!")]
        [DisplayName("Bairro")]
        [StringLength(100)]
        public string Bairro { get; set; }

        [Required(ErrorMessage ="O estado é obrigatório!!!")]
        [DisplayName("UF")]
        [StringLength(2)]
        [
            RegularExpression
            (
                @"^(ac|AC|al|AL|am|AM|ap|AP|ba|BA|ce|CE|df|DF|es|ES|go|GO|ma|MA|mg|MG|ms|MS|mt|MT|pa|PA|pb|PB|pe|PE|pi|PI|pr|PR|rj|RJ|rn|RN|ro|RO|rr|RR|rs|RS|sc|SC|se|SE|sp|SP|to|TO)$",
                ErrorMessage ="O estado desejado é inválido!!!"
            )
        ]
        public string Estado { get; set; }

        [Required(ErrorMessage ="A cidade é obrigatória!!!")]
        [DisplayName("Cidade")]
        [StringLength(50)]
        public string Cidade { get; set; }

    }
}