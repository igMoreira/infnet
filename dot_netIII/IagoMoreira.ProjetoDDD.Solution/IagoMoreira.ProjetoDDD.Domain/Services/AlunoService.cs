using IagoMoreira.ProjetoDDD.Domain.Interfaces.Repository;
using IagoMoreira.ProjetoDDD.Domain.Interfaces.Services;
using System;
using System.Collections.Generic;

namespace IagoMoreira.ProjetoDDD.Domain.Services
{
    public class AlunoService : IAlunoService
    {
        private readonly IAlunoRepository repo;

        public AlunoService(IAlunoRepository _repo)
        {
            repo = _repo;
        }

        public void AdicionarAluno(Aluno aluno)
        {
            repo.Add(aluno);
        }

        public void DeletarAluno(Aluno aluno)
        {
            repo.Remove(aluno);
        }

        public void EditarAluno(Aluno aluno)
        {
            repo.Update(aluno);
        }

        public Aluno ObterAlunoPorId(int id)
        {
            return repo.GetById(id);
        }

        public IEnumerable<Aluno> ObterAlunos()
        {
            return repo.GetAll();
        }

        public void Dispose()
        {
            repo.Dispose();
            GC.SuppressFinalize(this);
        }
    }
}
