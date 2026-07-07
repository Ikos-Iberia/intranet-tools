import React from 'react';
import { ViewType } from '../App';

interface SidebarProps {
  isCollapsed: boolean;
  setIsCollapsed: (value: boolean) => void;
  isMobileOpen: boolean;
  currentView: ViewType;
  onNavigate: (view: ViewType) => void;
  t: (key: string) => string;
}

const Sidebar: React.FC<SidebarProps> = ({ isCollapsed, setIsCollapsed, isMobileOpen, currentView, onNavigate, t }) => {
  const navItems = [
    { name: t('nav.public'), icon: 'grid_view', view: 'dashboard' as ViewType },
    { name: t('nav.it'), icon: 'terminal', view: 'it-tools' as ViewType },
    { name: t('nav.hr'), icon: 'badge', view: 'hr-tools' as ViewType },
  ];

  return (
    <>
      <div 
        className={`fixed inset-0 bg-slate-900/40 z-40 lg:hidden transition-opacity duration-300 ${isMobileOpen ? 'opacity-100' : 'opacity-0 pointer-events-none'}`}
      />

      <aside 
        className={`
          fixed inset-y-0 left-0 z-50 lg:static lg:z-auto
          flex flex-col h-full bg-white border-r border-slate-200 transition-all duration-300 ease-in-out shadow-sm
          ${isMobileOpen ? 'translate-x-0' : '-translate-x-full lg:translate-x-0'}
          ${isCollapsed ? 'w-20' : 'w-64'}
        `}
      >
        <div className="p-4 flex flex-col h-full overflow-hidden relative">
          <div 
            onClick={() => onNavigate('dashboard')}
            className={`flex items-center gap-3 mb-10 transition-all duration-300 cursor-pointer ${isCollapsed ? 'justify-center px-0' : 'px-2'}`}
          >
            <div className="bg-primary rounded-xl p-2.5 text-white shadow-md flex-shrink-0 transition-transform active:scale-95">
              <span className="material-symbols-outlined text-2xl">hub</span>
            </div>
            {!isCollapsed && (
              <div className="transition-opacity duration-300 whitespace-nowrap overflow-hidden">
                <h1 className="text-primary text-lg font-bold leading-none tracking-tight">Saniikos</h1>
                <p className="text-slate-500 text-[10px] mt-1.5 font-bold uppercase tracking-widest">{t('nav.corporate')}</p>
              </div>
            )}
          </div>

          <nav className="flex-1 flex flex-col gap-2">
            {navItems.map((item) => (
              <button 
                key={item.name}
                onClick={() => onNavigate(item.view)}
                title={isCollapsed ? item.name : ''}
                className={`
                  w-full flex items-center gap-3 px-3 py-3 rounded-xl transition-all duration-200 group relative
                  ${currentView === item.view 
                    ? 'bg-primary text-white font-bold shadow-lg shadow-primary/20' 
                    : 'text-slate-500 hover:bg-slate-50 hover:text-slate-900'}
                  ${isCollapsed ? 'justify-center px-0' : ''}
                `}
              >
                <span className={`material-symbols-outlined transition-colors ${currentView === item.view ? 'text-white' : 'text-slate-400 group-hover:text-slate-600'}`}>
                  {item.icon}
                </span>
                {!isCollapsed && (
                  <span className="text-sm whitespace-nowrap overflow-hidden transition-opacity duration-200">
                    {item.name}
                  </span>
                )}
                {isCollapsed && currentView === item.view && (
                  <div className="absolute right-0 w-1 h-6 bg-accent rounded-l-full" />
                )}
              </button>
            ))}
          </nav>

          <div className={`mt-auto pt-6 flex flex-col gap-2 ${isCollapsed ? 'items-center' : ''}`}>
             <button 
              onClick={() => onNavigate('helpdesk')}
              className={`
                flex items-center justify-center gap-2 bg-slate-900 hover:bg-slate-800 text-white font-bold rounded-xl text-sm transition-all shadow-md
                ${isCollapsed ? 'w-12 h-12' : 'w-full py-3.5'}
                ${currentView === 'helpdesk' ? 'ring-4 ring-slate-900/10 bg-slate-800' : ''}
              `}
            >
              <span className="material-symbols-outlined text-xl">help</span>
              {!isCollapsed && <span>{t('nav.helpdesk')}</span>}
            </button>
          </div>

          <button 
            onClick={() => setIsCollapsed(!isCollapsed)}
            className="hidden lg:flex absolute top-20 -right-3.5 w-7 h-7 bg-white border border-slate-200 rounded-full items-center justify-center text-slate-400 hover:text-primary hover:border-primary shadow-md z-10 transition-all group"
          >
            <span className={`material-symbols-outlined text-base font-bold transition-transform duration-300 ${isCollapsed ? 'rotate-180' : ''} group-hover:scale-110`}>
              chevron_left
            </span>
          </button>
        </div>
      </aside>
    </>
  );
};

export default Sidebar;