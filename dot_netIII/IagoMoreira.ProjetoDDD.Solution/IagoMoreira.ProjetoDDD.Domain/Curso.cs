using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace IagoMoreira.ProjetoDDD.Domain
{
    public class Curso
    {
        public int CursoId { get; set; }
        public string Nome { get; set; }
        public string Descrição { get; set; }
        public ICollection<Turma> Turmas { get; set; }
    }
}
