using IagoMoreira.ProjetoDDD.Domain;
using IagoMoreira.ProjetoDDD.Domain.Interfaces.Repository;
using IagoMoreira.ProjetoDDD.Infra.Data.Context;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IagoMoreira.ProjetoDDD.Infra.Data.Repositories
{
    public class AlunoRepository : RepositoryBase<Aluno>, IAlunoRepository
    {
        public Aluno ObterPorCPF(string cpf)
        {
            return set.FirstOrDefault(a => a.CPF == cpf);
        }
    }
}
