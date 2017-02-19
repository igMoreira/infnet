using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace IagoMoreira.ProjetoDDD.Domain
{
    public class Professor
    {
        public int ProfessorId { get; set; }
        public Guid Matricula { get; set; }
        public string Nome { get; set; }
        public ICollection<Curso> Cursos { get; set; }
    }
}
