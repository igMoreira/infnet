using AutoMapper;
using IagoMoreira.ProjetoDDD.Application.ViewModel;
using IagoMoreira.ProjetoDDD.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IagoMoreira.ProjetoDDD.Application.AutoMapper
{
    public class ViewModelToDomainMappingProfile : Profile
    {
        protected override void Configure()
        {
            CreateMap<AlunoViewModel, Aluno>();
        }
    }
}
