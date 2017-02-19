using IagoMoreira.ProjetoDDD.Domain.Interfaces.Repository.ReadOnly;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using IagoMoreira.ProjetoDDD.Domain;
using Dapper;

namespace IagoMoreira.ProjetoDDD.Infra.Data.Repositories.ReadOnly
{
    public class AlunoRepositoryReadOnly : RepositoryBaseReadOnly, IAlunoRepositoryReadOnly
    {
        public void Dispose()
        {
            GC.SuppressFinalize(this);
        }

        public IEnumerable<Aluno> GetAll()
        {
            using (var conn = Connection)
            {
                var sql = @"SELECT AlunoId, Matricula, Nome, DataNascimento, DataCriacao, CPF FROM ALUNOS";
                conn.Open();
                var alunos = conn.Query<Aluno>(sql);
                return alunos;
            }
        }

        public Aluno GetById(int id)
        {
            using (var conn = Connection)
            {
                var sql = @"SELECT AlunoId, Matricula, Nome, DataNascimento, DataCriacao, CPF FROM ALUNOS WHERE ALUNOID = '" + id + "'";
                conn.Open();
                var alunos = conn.Query<Aluno>(sql);
                return alunos.FirstOrDefault();
            }
        }

        public Aluno ObterPorCpf(string cpf)
        {
            using (var conn = Connection)
            {
                var sql = @"SELECT AlunoId, Matricula, Nome, DataNascimento, DataCriacao, CPF FROM ALUNOS WHERE CPF = '" + cpf + "'";
                conn.Open();
                var alunos = conn.Query<Aluno>(sql);
                return alunos.FirstOrDefault();
            }
        }
    }
}
