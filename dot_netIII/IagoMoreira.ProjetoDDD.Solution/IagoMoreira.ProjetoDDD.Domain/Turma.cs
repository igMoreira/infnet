using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace IagoMoreira.ProjetoDDD.Domain
{
    public class Turma
    {
        public int TurmaId { get; set; }
        public Guid CodTurma { get; set; }
        public ICollection<Curso> Cursos { get; set; }
    }
}
