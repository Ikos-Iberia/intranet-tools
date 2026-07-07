import React, { useState, useEffect } from 'react';
import Sidebar from './components/Sidebar';
import Header from './components/Header';
import Dashboard from './components/Dashboard';
import HelpDesk from './components/HelpDesk';
import Auth from './components/Auth';
import { Language } from './types';
import { translations } from './translations';

export type ViewType = 'dashboard' | 'it-tools' | 'hr-tools' | 'helpdesk';

const App: React.FC = () => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [searchQuery, setSearchQuery] = useState('');
  const [isCollapsed, setIsCollapsed] = useState(false);
  const [isMobileOpen, setIsMobileOpen] = useState(false);
  const [currentView, setCurrentView] = useState<ViewType>('dashboard');
  const [lang, setLang] = useState<Language>('en');

  // Close mobile sidebar on navigation or search
  useEffect(() => {
    if (isMobileOpen) setIsMobileOpen(false);
  }, [searchQuery, currentView]);

  const toggleMobileMenu = () => setIsMobileOpen(!isMobileOpen);

  const t = (key: string) => translations[lang][key] || key;

  const handleLogout = () => {
    setIsAuthenticated(false);
    setCurrentView('dashboard');
    setSearchQuery('');
  };

  if (!isAuthenticated) {
    return (
      <Auth 
        onLogin={() => setIsAuthenticated(true)} 
        t={t} 
        lang={lang} 
        onLangChange={setLang} 
      />
    );
  }

  return (
    <div className="flex h-screen overflow-hidden bg-slate-50 font-sans selection:bg-primary/10 selection:text-primary">
      <Sidebar 
        isCollapsed={isCollapsed} 
        setIsCollapsed={setIsCollapsed} 
        isMobileOpen={isMobileOpen}
        currentView={currentView}
        onNavigate={setCurrentView}
        t={t}
      />
      
      <div className="flex-1 flex flex-col min-w-0">
        <Header 
          onSearch={setSearchQuery} 
          toggleMobileMenu={toggleMobileMenu}
          currentView={currentView}
          onNavigate={setCurrentView}
          lang={lang}
          onLangChange={setLang}
          onLogout={handleLogout}
          t={t}
        />
        
        <main className="flex-1 flex flex-col overflow-hidden relative">
          {currentView === 'helpdesk' ? (
            <HelpDesk onBack={() => setCurrentView('dashboard')} t={t} />
          ) : (
            <Dashboard 
              searchQuery={searchQuery} 
              currentView={currentView} 
              onNavigate={setCurrentView}
              t={t}
              lang={lang}
            />
          )}
          
          {/* Footer */}
          <footer className="hidden md:flex h-12 flex-shrink-0 border-t border-slate-200 bg-white px-8 items-center justify-between text-[10px] text-slate-400 font-bold uppercase tracking-widest">
            <div className="flex gap-10">
              <span>© 2026 Saniikos Corporate Portal v1</span>
              <div className="flex items-center gap-1.5 text-green-600">
                <span className="w-1.5 h-1.5 rounded-full bg-current animate-pulse"></span>
                <span>Systems Active</span>
              </div>
            </div>
            <div className="flex gap-6 items-center">
              <span className="text-slate-400 normal-case tracking-normal text-[11px] font-medium italic">Made by José Antonio Acebedo Aragón</span>
              <div className="flex items-center gap-1 text-slate-500 bg-slate-50 px-2 py-1 rounded">
                <span className="material-symbols-outlined text-sm">support</span>
                <span>IT Support: int. 8706</span>
              </div>
            </div>
          </footer>
        </main>
      </div>
    </div>
  );
};

export default App;