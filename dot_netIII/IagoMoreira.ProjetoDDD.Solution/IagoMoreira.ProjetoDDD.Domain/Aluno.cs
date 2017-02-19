using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace IagoMoreira.ProjetoDDD.Domain
{
    public class Aluno
    {

        public Aluno()
        {
            Matricula = Guid.NewGuid();
        }

        public int AlunoId { get; set; }
        public Guid Matricula { get; set; }
        public string Nome { get; set; }
        public DateTime DataNascimento { get; set; }
        public DateTime DataCriação { get; set; }
        public string CPF { get; set; }

    }
}
