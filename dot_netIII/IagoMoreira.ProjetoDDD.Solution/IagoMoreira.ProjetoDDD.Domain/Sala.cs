using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace IagoMoreira.ProjetoDDD.Domain
{
    public class Sala
    {
        public int SalaId { get; set; }
        public string Identificator { get; set; }
        public ICollection<Turma> Turmas { get; set; }
    }
}
