using IagoMoreira.ProjetoDDD.Application.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IagoMoreira.ProjetoDDD.Application.Interfaces
{
    public interface IAlunoAppService
    {
        void AdicionarAluno(AlunoViewModel alunoViewModel);
        IEnumerable<AlunoViewModel> ObterAlunos();
        void EditarAluno(AlunoViewModel aluno);
        void DeletarAluno(AlunoViewModel aluno);
        AlunoViewModel ObterAlunoPorId(int id);
    }
}
