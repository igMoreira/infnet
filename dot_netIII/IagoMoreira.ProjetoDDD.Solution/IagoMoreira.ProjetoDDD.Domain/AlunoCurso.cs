using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace IagoMoreira.ProjetoDDD.Domain
{
    public class AlunoCurso
    {
        public int AlunoCursoId { get; set; }
        public int AlunoId { get; set; }
        public int CursoId { get; set; }
    }
}
