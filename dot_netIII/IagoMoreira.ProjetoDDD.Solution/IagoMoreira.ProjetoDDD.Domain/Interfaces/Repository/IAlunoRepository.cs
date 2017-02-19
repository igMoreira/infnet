using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IagoMoreira.ProjetoDDD.Domain.Interfaces.Repository
{
    public interface IAlunoRepository : IRepositoryBase<Aluno>
    {
        Aluno ObterPorCPF(string cpf);
    }
}
