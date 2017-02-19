using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;

namespace IagoMoreira.ProjetoDDD.Domain.Interfaces.Services
{
    public interface IAlunoService : IDisposable
    {
        void AdicionarAluno(Aluno aluno);
        IEnumerable<Aluno> ObterAlunos();
        void EditarAluno(Aluno aluno);
        void DeletarAluno(Aluno aluno);
        Aluno ObterAlunoPorId(int id);
    }
}
