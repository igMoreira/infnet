using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IagoMoreira.ProjetoDDD.Domain.Interfaces.Repository.ReadOnly
{
    public interface IAlunoRepositoryReadOnly : IDisposable
    {
        Aluno GetById(int id);
        IEnumerable<Aluno> GetAll();
        Aluno ObterPorCpf(string cpf);
    }
}
